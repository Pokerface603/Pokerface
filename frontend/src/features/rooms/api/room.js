import { axios } from "@lib/axios";

export const getRooms = async (mode) => {
  const { data } = await axios.get(`/rooms/mode/${mode}`);
  return data;
};

export const searchRoomsWithKeyword = async (mode, title) => {
  const { data } = await axios.get(`/rooms/mode/${mode}/title/${title}`);
  return data;
};

export const quickStart = async () => {
  const data = await axios.get(`/matches/{member_id}`);
  return data;
};
