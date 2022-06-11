const functions = require("firebase-functions");
const express = require('express');
const cors = require('cors');
const Verifikasi = require('./config/config');
const app = express();
app.use(express.json());
app.use(cors());


exports.get_dataVerifikasi =  functions.https.onRequest(async (req,res) => {
    // get list data form ocr result
    const Snapshot = await Verifikasi.get();
    const list = Snapshot.docs.map((doc) => ({id:doc.id, ...doc.data()}))
    res.send(list)
});

exports.add_dataVerifikasi = functions.https.onRequest(async (req, res) => {
    const data = req.body
    // add result data from OCR insert to db
    await Verifikasi.add(data);
    res.send({msg: "Data verifikasi berhasil ditambahkan"});
});

// retrieve and add data from ocr result
app.get('/', this.get_dataVerifikasi);
app.post('/api/verifikasi-data', this.add_dataVerifikasi);


app.listen(5000, () => console.log("Server running 5000"));

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//   functions.logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });
