const express = require('express');
const bodyParser = require('body-parser');

let app = express();

// Config bodyParser and cookieParser
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

if (app.get('env') === null || typeof app.get('env') === 'undefined') {
  app.set('env', 'development');
}

app.post('/api/login', (req, res, next) => {
  console.log(req.body);
  if (req.body.username === 'username' && req.body.password === 'password') {
    return res.status(200).json({ status: true});
  } else {
    return res.status(400).json({ status: false});
  }
});

// error handler
app.use(errorHandler);

app.listen(3000);

function notFound(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
}

function errorHandler(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  return res.json({});
}

module.exports = app;