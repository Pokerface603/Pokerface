import Button from "@component/Button";
import Input from "@component/Input";
import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function LobbyPage() {
  const [sessionId, setSessionId] = useState("");

  const navigate = useNavigate();

  const onChangeSessionId = (e) => {
    setSessionId(e.target.value);
  };

  const onClickEnter = () => {
    navigate(`game/${sessionId}`);
  };

  return (
    <div>
      <h1>로비 페이지 입니다. </h1>
      <Input onChange={onChangeSessionId}  />
      <Button onClick={onClickEnter} label={"방 입장하기"}></Button>
    </div>
  );
}

export default LobbyPage;
