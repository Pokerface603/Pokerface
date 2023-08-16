import { axios } from "@lib/axios";

export const getLobbies = async (email) => {
  const { data } = await axios.get(`/lobbies/${email}`);
  return data ?? [];
};

export const getFriends = async (email) => {
  const { data } = await axios.get(`/lobbies/friend/${email}`);
  return data ?? [];
};
