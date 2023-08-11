import { axios } from "@lib/axios.js";

export const login = (email, password) => {
  const body = {
    username: email,
    password: password,
  };
  
  return axios.post(`/members/login`, body);
};