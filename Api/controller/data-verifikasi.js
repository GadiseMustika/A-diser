const Verifikasi = require('../config/config');
const functions = require('firebase-functions');

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

