const http = require("http");

let webhookEnabled = false;
let lastSignal = null;

const PORT = process.env.PORT || 3000;

function sendJson(res, status, data) {
    res.writeHead(status, {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*"
    });

    res.end(JSON.stringify(data));
}

function readBody(req) {
    return new Promise((resolve, reject) => {
        let body = "";

        req.on("data", chunk => {
            body += chunk;
        });

        req.on("end", () => {
            resolve(body);
        });

        req.on("error", reject);
    });
}

const server = http.createServer(async (req, res) => {

    // STATUS
    if (req.method === "GET" && req.url === "/status") {
        return sendJson(res, 200, {
            success: true,
            webhook: webhookEnabled ? "ON" : "OFF",
            status: webhookEnabled ? "ONLINE" : "OFFLINE",
            lastSignal
        });
    }

    // TURN ON
    // GET + POST दोनों काम करेंगे
    if (
        (req.method === "GET" || req.method === "POST") &&
        req.url === "/on"
    ) {
        webhookEnabled = true;

        return sendJson(res, 200, {
            success: true,
            webhook: "ON",
            status: "ONLINE"
        });
    }

    // TURN OFF
    // GET + POST दोनों काम करेंगे
    if (
        (req.method === "GET" || req.method === "POST") &&
        req.url === "/off"
    ) {
        webhookEnabled = false;

        return sendJson(res, 200, {
            success: true,
            webhook: "OFF",
            status: "OFFLINE"
        });
    }

    // TRADINGVIEW / APK WEBHOOK
    if (req.method === "POST" && req.url === "/webhook") {

        if (!webhookEnabled) {
            return sendJson(res, 403, {
                success: false,
                message: "Webhook is OFF"
            });
        }

        const body = await readBody(req);

        let signal;

        try {
            signal = JSON.parse(body);
        } catch {
            signal = {
                raw: body
            };
        }

        lastSignal = {
            signal,
            time: new Date().toISOString()
        };

        console.log("SIGNAL RECEIVED:", lastSignal);

        return sendJson(res, 200, {
            success: true,
            message: "Signal received",
            signal
        });
    }

    // HOME
    if (req.method === "GET" && req.url === "/") {
        return sendJson(res, 200, {
            service: "JK HUB WEBHOOK",
            webhook: webhookEnabled ? "ON" : "OFF",
            status: webhookEnabled ? "ONLINE" : "OFFLINE"
        });
    }

    // NOT FOUND
    return sendJson(res, 404, {
        success: false,
        message: "Not found"
    });
});

server.listen(PORT, "0.0.0.0", () => {
    console.log(`JK HUB Webhook running on port ${PORT}`);
});
