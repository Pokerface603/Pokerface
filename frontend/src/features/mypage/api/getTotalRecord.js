import { axios } from "lib/axios";

export const getTotalRecord = async (email) => {
  try {
    const response = await axios.get(`/details/count/${email}`);

    return response.data;
  } catch (error) {
    console.log("에러 : ", error);
    throw error;
  }
};
