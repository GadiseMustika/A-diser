const functions = require("firebase-functions");
const express = require('express');
const cors = require('cors');
const {Verifikasi,Surat} = require('./config/config');
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



exports.add_dataSurat = functions.https.onRequest(async (req, res) => {
    await Surat.add( {
        no_surat: req.body.no_surat,
        jenis_surat: req.body.jenis_surat,
        nik: req.body.nik,
        nama_penduduk: req.body.nama_penduduk,
        tanggal_permohonan: req.body.tanggal_permohonan,
        tanggal_selesai: req.body.tanggal_selesai
    });
    res.send({msg: "Surat berhasil ditambahkan!"});
});

exports.get_dataSurat =  functions.https.onRequest(async (req,res) => {
    // get list data form ocr result
    const Snapshot = await Surat.get();
    const list = Snapshot.docs.map((doc) => ({id:doc.id, ...doc.data()}))
    res.send(list)
});


// retrieve and add data from ocr result
app.get('/', this.get_dataVerifikasi);
app.post('/api/verifikasi-data', this.add_dataVerifikasi);

// add data surat and retrieve for detailed surat
app.post('/admin/tambah-surat', this.add_dataSurat);
app.get('/surat', this.get_dataSurat);

app.listen(5000, () => console.log("Server running 5000"));

