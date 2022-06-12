# A-diser
![cover](cover.png)
### Automatic District Service Application
##### CC22-PC417 Bangkit Academy 2022
##### Team: 
1.M2006F0591 - Muhammad Salman Ikrar Musyaffa - Universitas Brawijaya <br>
2.M2313G2715 - Irena Kusuma Dewi - Universitas Siliwangi <br>
3.C7307F2631 - Gadis Mustika  - Universitas Raharja<br>

***
#### Introduction 
A-diser is a service application for village population administration activities, this application is to assist the village office in conducting population data collection without having to do it manually and also helping residents to send a letter of requirements without having to queue up to the village office just waiting for the processing results.

### Technology 
The technology used in this project is kotlin, nodejs, python,firebase,and google cloud platform products.

### Android learning path
#### Clone project app
1. clone this project in localmachine
2. run in Android Studio

#### Technology featured
<ul>
<li> <b>Kotlin</b> is a cross-platform, statically typed, general-purpose programming language with type inference. Kotlin is designed to interoperate fully with Java, and the JVM version of Kotlin's standard library depends on the Java Class Library, but type inference allows its syntax to be more concise
<li> <b> Retrofit</b> is the addition of new technology or features to older systems, for example: power plant retrofit, improving power plant efficiency, increasing output or reducing emissions home energy
</ul>

### Machine learning path
#### Generate OCR modeling 
  1.clone git repository in your localmachine<br>
  2.open project python editor like pycharm,spyder on local machine <br>
  3.install python packages following commands: `pip install tesseract-ocr`, `pip install pytesseract` and `pip install opencv`  <br>
  4.run project in your python editior.

#### Technology featured
<ul>
  <li> <b>Python</b> is a high-level, interpreted, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation. Python is dynamically-typed and garbage-collected
  <li> <b>Tesseract </b> Python-tesseract is an optical character recognition (OCR) tool for python. That is, it will recognize and “read” the text embedded in images.
  <li> <b>Opencv</b>  a library of programming functions mainly aimed at real-time computer vision. Originally developed by Intel, it was later supported by Willow Garage then Itseez. The library is cross-platform and free for use under the open-source Apache 2 License.
</ul>

### Cloud Computing learning path
#### Deploying Api to Gloud Functions
1.Make sure you have created a project in firebase and connect with your project in GCP <br>
2.Open the project directory on the local machine <br>
3.Install the firebase and firebase-admin CLI using `npm.` Note that you will need to install `Node.js` and npm. to download and install the `firebase CLI`
  <br> run the following command: `npm install -g firebase-tools` <br>
4.Login to Firebase using a Google Account by running the following command: `firebase login` This command connects a local computer to Firebase and grants access to your Firebase project . <br>
  5.To initialize a new Firebase project, run the following command from within your app directory: `firebase init` The firebase init command will guide you through setting up your project directory and some Firebase products. During project initialization, the Firebase CLI prompts to complete the following tasks: Select Firebase product : functions : Configure and deploy cloud functions Select the default Firebase project. <br>
  6.To deploy to a Firebase project, run the following command from your project directory: `firebase deploy --only functions`

#### Technology featured
<ul>
<li> <b>Node js</b> It is suitable for those who need real time communication between client and server and The single-threaded event system is very fast when handling many requests at once from clients
<li> <b>Framework Express Js</b> there is no need to use the default http module from NodeJS. This framework offers several features such as routing, view rendering, and supports middleware in other words it will save a lot of time in Node.js application development.
<li><b>Google Cloud Platform</b> Grow from prototype to production without having to think about capacity, reliability, or performance, Easily capture, manage, process, and visualize data with Google Cloud data analytics products.
<li> <b>Firebase</b> Apps in a project share features like Real-time Database and Analytics
</ul>

### Traning OCR Model
in this project using the Google cloud platform Vertex AI by creating a jupyter notebook workbench to analyze the results of OCR data