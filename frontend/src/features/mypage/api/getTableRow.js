import { axios } from "lib/axios";

export const getHistoryTableRow = async (email, page) => {
  try {
    const response = await axios.get(`/details/member/${email}/?page=${page}`);

    return response.data ?? [];
  } catch (error) {
    console.log("에러 : ", error);
    throw error;
  }
};
