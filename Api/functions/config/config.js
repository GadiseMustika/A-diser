const admin = require("firebase-admin");
const serviceAccount = require("../adiser-firebase-admin.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://adiser-default-rtdb.firebaseio.com"
});
const db = admin.firestore();
// const dissconect = ref(db, "jaringan dinonaktif");
const Verifikasi = db.collection('verifikasi-ktp');
module.exports = Verifikasi;