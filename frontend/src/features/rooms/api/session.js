import { axios } from "@lib/axios";

export async function getToken(roomData) {
  const sessionId = await createSession(roomData);
  return await createToken(sessionId);
}

async function createSession({ roomName, gameMode, isPrivate, roomPassword }) {
  const response = await axios.post(
    "/sessions",
    {
      customSessionId: roomName,
      title: roomName,
      gameMode,
      isPrivate,
      roomPassword,
    },
    {
      headers: { "Content-Type": "application/json" },
    }
  );
  return response.data; // The sessionId
}

async function createToken(sessionId) {
  const response = await axios.post(
    "/sessions/" + sessionId + "/connections",
    {},
    {
      headers: { "Content-Type": "application/json" },
    }
  );
  return response.data;
}
