import { axios } from "@lib/axios";

export const getRooms = async (pageNum, mode) => {
  const { data } = await axios.post(`/rooms/findByGameMode`, {
    pageNum,
    gameMode: mode,
  });
  return data;
};

export const searchRoomsWithKeyword = async (mode, title) => {
  const { data } = await axios.get(`/rooms/mode/${mode}/title/${title}`);
  return data;
};

export const quickStart = async (email) => {
  const { data } = await axios.get(`/lobbies/match/${email}`);
  return data;
};

export const leaveRoom = async ({ email, sessionId }) => {
  await axios.post(`/rooms/disconnect`, {
    email,
    sessionId,
  });
};
