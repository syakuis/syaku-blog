module.exports = (req, res, next) => {
  if (req.method === 'POST' && req.path === '/login') {
    if (req.body.username === 'admin' && req.body.password === '1234') {
      res.status(200).json({ error: false, message: '' });
    } else {
      res.status(400).json({ error: true, message: 'wrong password' });
    }
  } else {
    next();
  }
};
