import { configureStore } from "@reduxjs/toolkit";
import userSlice from "./userSlice";
// 새로고침 시 user 정보가 초기화 되는 현상을 방지하기 위해 redux-persist 사용
import { combineReducers } from "@reduxjs/toolkit";
import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";

const reducers = combineReducers({
  user: userSlice,
});

const persistConfig = {
  // root 지점에서부터 데이터 저장
  key: "root",
  storage,
  // whitelist: 유지하고 싶은 값을 배열로 전달
  whitelist: ["user"],
};

const persistedReducer = persistReducer(persistConfig, reducers);

export default configureStore({
  reducer: persistedReducer,
});
