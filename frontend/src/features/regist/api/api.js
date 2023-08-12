import { axios } from "@lib/axios.js";

export const regist = (email, nickname, password) => {
  const body = {
    email: email,
    nickname: nickname,
    password: password,
  };

  return axios.post("/members", body);
};

export const checkEmailAvailability = async (email) => {
  try {
    const response = await axios.get(`/members/check/email/${email}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const checkNicknameAvailability = async (nickname) => {
  try {
    const response = await axios.get(`/members/check/nickname/${nickname}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};
