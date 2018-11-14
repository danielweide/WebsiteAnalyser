const express = require('express');
const app = express();
const morgan = require('morgan');
const bodyParser = require('body-parser');
const ev = require('express-validation');
const PORT = process.env.PORT;

const verifyRoute = require('./api/verify');

app.use(morgan('dev'));
app.use(bodyParser.json());

//Control of CORS Errors --> Append Security Header
app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "ContentType, Authorization");
  // res.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS");

  if (req.method === 'OPTIONS') {
    res.header("Access-Control-Allow-Methods", "PUT, POST, PATCH, DELETE, GET");
    return res.status(200).json({});
  }
  next(); //Allow other routes to take over
});

app.get('/', (req, res, next) =>{
  res.status(200).send("<h2> Bienvenue sur la page d'accueil de la Rest Api pour Abricot</h2>");
});

app.use('/api/verify', verifyRoute);


//Error handler for lost routes
app.use((req, res, next) => {
  const error = new Error("Not Found");
  error.status = 404;
  next(error);
});

//General Error handler
app.use((error, req, res, next) => {
  if (error instanceof ev.ValidationError) return res.status(error.status).json(error.errors[0].messages);
  else{
    res.status(error.status || 500);
    res.json({
      error: {
        message: error.message
      }
    });
  }
});

module.exports = app;
