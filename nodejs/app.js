/* app.js */

const express = require("express");

const PORT = process.env.PORT || "8080";
const app = express();

app.get("/", (req, res) => {
  res.send("Hello World\n");
});

app.get("/version", (req, res) => {
  res.send("1.0\n");
});

app.listen(parseInt(PORT, 10), () => {
  console.log(`Listening for requests on http://localhost:${PORT}`);
});
