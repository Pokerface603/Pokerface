import { axios } from "@lib/axios";

export const getHistoryDetails = async (email, historyID) => {
  const { data } = await axios.get(`/histories/${historyID}/${email}`);
  return data;
};
