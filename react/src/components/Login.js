import React from 'react';
import PropTypes from 'prop-types';

const propTypes = {
  children: PropTypes.node.isRequired,
  onChange: PropTypes.func.isRequired,
  username: PropTypes.string.isRequired,
  password: PropTypes.string.isRequired,
};

class Login extends React.Component {
  constructor(props) {
    super(props);

    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(e) {
    const { id, value } = e.target;
    const { onChange } = this.props;
    onChange(value, id);
  }

  render() {
    const { children, username, password } = this.props;

    return (
      <form>
        <div className="form-group">
          <label htmlFor="username">username</label>
          <input
            type="text"
            className="form-control"
            id="username"
            placeholder="계정을 입력하세요."
            onChange={this.handleChange}
            value={username}
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">password</label>
          <input
            type="password"
            className="form-control"
            id="password"
            placeholder="암호를 입력하세요."
            onChange={this.handleChange}
            value={password}
          />
        </div>
        {children}
      </form>
    );
  }
}

Login.propTypes = propTypes;

export default Login;
