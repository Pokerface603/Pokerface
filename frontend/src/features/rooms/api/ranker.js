import { axios } from "@lib/axios";

export const getRankers = async () => {
  const response = await axios.get(`/members/ranking/top`);

  return response.data;
};
