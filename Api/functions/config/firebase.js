const firebase = require("firebase");
const firebaseConfig = {
  apiKey: "AIzaSyCxmzuJ1-IOdVJV2JrcOcWWJMePcYJUEX8",
  authDomain: "adiser.firebaseapp.com",
  projectId: "adiser",
  storageBucket: "adiser.appspot.com",
  messagingSenderId: "684305831994",
  appId: "1:684305831994:web:8716493b426cf3eb4c7d03",
  measurementId: "G-VKRG3E6YHJ"
};

// Initialize Firebase,anayltic,and firestore
firebase.initializeApp(firebaseConfig);
module.exports = {firebase};