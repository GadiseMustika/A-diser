module.exports = (sequelize, Sequelize) => {
    const suratNikah =  sequelize.define("keterangan_nikah", {
        no_surat: {
            type: Sequelize.INTEGER
        },
        nik_penduduk:{
            type: Sequelize.STRING
        },
        nama_penduduk:{
            type: Sequelize.STRING
        },
        tanggal_permohonan:{
            type: Sequelize.STRING
        },
        tanggal_selesai: {
            type: Sequelize.STRING
        },
        status_permohonan:{
            type: Sequelize.BOOLEAN
        },
    });

    return suratNikah;
}