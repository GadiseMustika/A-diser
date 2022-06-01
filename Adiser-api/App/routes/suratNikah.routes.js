module.exports = function(app){
    const keteranganNikah = require("../controllers/suratNikah.controller");

    // tambah data
    app.post(
        "/api/tambahData",
        keteranganNikah.add
    );

    // get All data
    app.get(
        "/api/keteranganNikah",
        keteranganNikah.findAll
    );

    // get data by id
    app.get(
        "/api/keteranganNikah/:id",
        keteranganNikah.findByPk
    );

    app.put(
          "/api/updateData/:id",
          keteranganNikah.update);

    app.delete(
          "/api/hapusData/:id",
           keteranganNikah.delete
    );
}