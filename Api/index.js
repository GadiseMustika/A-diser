const express = require('express');
const cors = require('cors');
const app = express();
app.use(express.json());
const data_verifikasi = require("./controller/data-verifikasi");
app.use(cors());

// onDisconnect(Verifikasi.dissconect)


app.get('/', data_verifikasi.get_dataVerifikasi);
app.post('/api/verifikasi-data', data_verifikasi.add_dataVerifikasi)

app.listen(5000, () => console.log("Server running 5000"));