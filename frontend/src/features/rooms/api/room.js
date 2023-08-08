import { getToken } from "./session";
import { axios } from "@lib/axios";

export const makeRoom = async (roomData) => {
  return await getToken(roomData);
};

export const getRooms = async (mode) => {
  const { data } = await axios.get(`/rooms/mode/${mode}`);
  return data;
};
