import Axios from "axios";
import { API_URL } from "@config/index";

export const axios = Axios.create({
  baseURL: API_URL,
});

axios.interceptors.request.use(
  (config) => {
    // getToken() - 클라이언트에 저장되어 있는 액세스 토큰을 가져오는 함수
    const accessToken = localStorage.getItem("authorization");

    config.headers["Content-Type"] = "application/json";
    config.headers["Authorization"] = `${accessToken}`;

    return config;
  },
  (error) => {
    console.log(error);
    return Promise.reject(error);
  }
);
