import React from "react";
import GameModeButton from "./GameModeButton";

function GameModeSelector({ gameMode, onSelectMode }) {
  return (
    <div className="flex gap-3">
      <GameModeButton
        gameMode={"NORMAL"}
        isSelected={gameMode === "NORMAL"}
        onClick={onSelectMode}
      />
      <GameModeButton
        gameMode={"BLIND"}
        isSelected={gameMode === "BLIND"}
        onClick={onSelectMode}
      />
      <GameModeButton
        gameMode={"EMOTION"}
        isSelected={gameMode === "EMOTION"}
        onClick={onSelectMode}
      />
    </div>
  );
}

export default GameModeSelector;
