from flask import Flask, render_template, request, jsonify

app = Flask(__name__)

def knapsack(values, weights, capacity):
    n = len(values)
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]
    
    for i in range(1, n + 1):
        for w in range(1, capacity + 1):
            if weights[i-1] <= w:
                dp[i][w] = max(values[i-1] + dp[i-1][w - weights[i-1]], dp[i-1][w])
            else:
                dp[i][w] = dp[i-1][w]
    
    # Backtrack to find selected items
    selected = []
    w = capacity
    for i in range(n, 0, -1):
        if dp[i][w] != dp[i-1][w]:
            selected.append(i-1)
            w -= weights[i-1]
    
    return dp[n][capacity], selected

@app.route("/")
def home():
    return render_template("index.html")

@app.route("/solve", methods=["POST"])
def solve():
    data = request.get_json()
    values = list(map(int, data["values"].split(",")))
    weights = list(map(int, data["weights"].split(",")))
    capacity = int(data["capacity"])
    
    max_value, selected_items = knapsack(values, weights, capacity)
    
    return jsonify({
        "max_value": max_value,
        "selected_items": selected_items
    })

if __name__ == "__main__":
    app.run(debug=True)