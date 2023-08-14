import { axios } from "@lib/axios";

export async function createRoom({
  roomName,
  sessionId,
  gameMode,
  isPrivate,
  roomPassword,
}) {
  const response = await axios.post(
    "/sessions",
    {
      customSessionId: sessionId,
      title: roomName,
      gameMode,
      isPrivate,
      roomPassword,
    },
    {
      headers: { "Content-Type": "application/json" },
    }
  );

  return response.data;
}

export async function participateRoom(sessionId, password) {
  const response = await axios
    .post(
      "/sessions/" + sessionId + "/connections",
      {
        password,
      },
      {
        headers: { "Content-Type": "application/json" },
      }
    )
    .catch((error) => {
      console.log(error);
    });
  return response.data;
}
