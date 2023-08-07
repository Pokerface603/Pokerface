import { getToken } from "./session";

export const makeRoom = async (roomData) => {
  return await getToken(roomData);
};
