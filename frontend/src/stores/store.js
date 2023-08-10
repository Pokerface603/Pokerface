import { configureStore } from "@reduxjs/toolkit";
import userSlice from "./userSlice";


// configureStore()로 스토어를 만들어 준다. 리듀서는 userSlice.js에서 가져온다.
export default configureStore({
  reducer: {
    user: userSlice,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});