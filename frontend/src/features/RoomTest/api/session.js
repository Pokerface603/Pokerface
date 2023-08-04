import { axios } from "@lib/axios";

export async function getToken(mySessionId) {
  const sessionId = await createSession(mySessionId);
  return await createToken(sessionId);
}

async function createSession(sessionId) {
  const response = await axios.post(
    "/sessions",
    { customSessionId: sessionId },
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
