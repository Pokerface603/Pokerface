import Button from "@component/Button";
import React from "react";

function GameModeButton({ gameMode, isSelected, onClick }) {
  return (
    <Button
      width={"33%"}
      label={gameMode}
      background={isSelected ? "var(--ocher)" : "var(--brown)"}
      color={isSelected ? "white" : "rgba(0, 0, 0, 0.50)"}
      onClick={() => onClick(gameMode)}
    />
  );
}

export default GameModeButton;
