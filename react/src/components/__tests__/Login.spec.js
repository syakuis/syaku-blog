import React from 'react';
import { shallow } from 'enzyme';
import Login from '../Login';

const onChange = jest.fn();

const login = shallow(
  <Login onChange={onChange} username="" password="">
    <button type="submit" className="btn btn-primary">
      로그인
    </button>
  </Login>,
);

test('렌터링 테스트한다.', () => {
  expect(login.find('#username').length).toBe(1);
  expect(login.find('#password').length).toBe(1);
  expect(login.find('button').length).toBe(1);
});

test('change 이벤트로 계정을 변경한다.', () => {
  login.find('#username').simulate('change', {
    target: { id: 'username', value: 'test' },
  });

  expect(onChange).toHaveBeenCalledWith('test', 'username');
});

test('change 이벤트로 암호를 변경한다.', () => {
  login.find('#password').simulate('change', {
    target: { id: 'password', value: '1234' },
  });
  expect(onChange).toHaveBeenCalledWith('1234', 'password');
});
