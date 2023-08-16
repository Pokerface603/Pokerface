import { axios } from "@lib/axios";

export const getGameResultData = async (email) => {
  const { data } = await axios.get(`/histories/${email}`);
  console.log(data);
  return data;
};