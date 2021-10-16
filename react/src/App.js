import React from 'react';
import { HashRouter as Router, Route } from 'react-router-dom';
import LoginContainer from '_containers/LoginContainer';

import 'bootstrap/dist/css/bootstrap.css';
import '@fortawesome/fontawesome-free/css/all.css';

const App = () => (
  <Router>
    <Route path="/login" component={LoginContainer} />
  </Router>
);

export default App;
