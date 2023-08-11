import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
    name: "user",
    initialState: {
      nickname: "",
      email: "",
      authorization: "",
      authorizationRefresh: "",
      isLogin: null,
    },
    reducers: {
      // login 성공 시 
      loginUser: (state, action) => {
        // nickname, email에 API 값 받아오기
        state.nickname = action.payload.nickname;
        state.email = action.payload.email;
        state.authorization= action.payload.authorization;
        state.authorizationRefresh = action.payload.authorizationRefresh;
        state.isLogin = true;
      },
      // logout 처리
      clearUser: (state) => {
        state.nickname = "";
        state.email = "";
        state.authorization= "";
        state.authorizationRefresh="";
        state.isLogin = false;
      },
    },
  });

export const { loginUser, clearUser} = userSlice.actions;
export default userSlice.reducer;