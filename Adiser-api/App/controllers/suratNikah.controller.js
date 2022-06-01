const db = require("../models");
const suratNikah = db.keteranganNikah;

// add data keterangan Nikah
exports.add = (req,res) => {
    suratNikah.create({
        no_surat: req.body.no_surat,
        jenis_surat: req.body.jenis_surat,
        nik_penduduk: req.body.nik_penduduk,
        nama_penduduk: req.body.nama_penduduk,
        tanggal_permohonan: req.body.tanggal_permohonan,
        tanggal_selesai: req.body.tanggal_selesai,
        status_permohonan: req.body.status_permohonan,
    }).then((keterangan_nikah) => {
        res.status(200).json({
            status: true,
            message: "Data berhasil ditambahkan!"
        });
    });
};

// get all data keterangan Nikah
exports.findAll = (req,res) => {
    suratNikah.findAll().then((keterangan_nikah) => {
        res.status(200).json({
            status: true,
            data: keterangan_nikah,
        });
    });
};

// find a data by Id
exports.findByPk = (req,res) => {
    suratNikah.findByPk(req.params.id).then((keteranganNikah) => {
        res.status(200).json({
            status: true,
            data: keteranganNikah,
        });
    });
};

//  Update a data
    exports.update = (req,res) => {
        const id = req.params.id;
        suratNikah.update(
            {
            no_surat: req.body.no_surat,
            nama_penduduk: req.body.nama_penduduk,
            tanggal_permohonan: req.body.tanggal_permohonan,
            tanggal_selesai: req.body.tanggal_selesai,
            status_permohonan: req.body.status_permohonan
            },
            {where: {id: req.params.id}}
        ).then(() => {
            res.status(200).json({
                status: true,
                message: "Data berhasil diperbaharui" 
            });
        });
    };

    //  delete data by id
    exports.delete = (req, res) => {
        const id = req.params.id;
        suratNikah.destroy({
            where: {id: id},
        }).then(() => {
            res.status(200).json({
                status: true,
                message: "Data berhasil dihapus"
            });
        });
    };
