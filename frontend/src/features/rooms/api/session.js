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
      if (error.response.status === 405) {
        alert("이미 꽉찬 방입니다.");
      }
    });

  return response.data;
}
