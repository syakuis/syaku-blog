import React from 'react';
import axios from 'axios';
import qs from 'qs';
import Login from '_components/Login';

class LoginContainer extends React.Component {
  constructor(props) {
    super(props);

    this.handleChange = this.handleChange.bind(this);
    this.handleLogin = this.handleLogin.bind(this);

    this.state = {
      username: '',
      password: '',
    };
  }

  handleLogin() {
    const { username, password } = this.state;
    axios
      .post(
        '/api/login',
        qs.stringify({
          username,
          password,
        }),
      )
      .then(res => {
        console.log(res);
      });
    return null;
  }

  handleChange(value, id) {
    this.setState(() => ({
      [id]: value,
    }));
  }

  render() {
    const { username, password } = this.state;
    return (
      <Login onChange={this.handleChange} username={username} password={password}>
        <button type="button" className="btn btn-primary" onClick={this.handleLogin}>
          로그인
        </button>
      </Login>
    );
  }
}

export default LoginContainer;
