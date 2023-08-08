import { axios } from "lib/axios";

export const getHistoryTableRow = (number) => {
  axios
    .get("/details")
    .then((respose) => {
      console.log(respose.data);
    })
    .catch((error) => {
      console.log("에러 : ", error);
    });
};
