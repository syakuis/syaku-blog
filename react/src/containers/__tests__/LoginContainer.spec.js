import React from 'react';
import { mount } from 'enzyme';
import LoginContainer from '../LoginContainer';

test('계정을 변경해야 한다.', () => {
  const wrapper = mount(<LoginContainer />);
  wrapper.find('#username').simulate('change', {
    target: { id: 'username', value: '1234' },
  });
  expect(wrapper.find('Login').length).toBe(1);
  expect(wrapper.state().username).toBe('1234');

  const instance = wrapper.instance();
  instance.handleChange('test', 'username');
  expect(wrapper.state().username).toBe('test');
});
