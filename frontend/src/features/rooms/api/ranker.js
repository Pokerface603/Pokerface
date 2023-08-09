import { axios } from "@lib/axios";

export const getRankers = async () => {
  return await axios.get(`/members/ranking/top`);
};
