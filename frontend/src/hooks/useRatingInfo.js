import { getTotalRecord } from "@feature/mypage/api/getTotalRecord";
import { useEffect, useState } from "react";

function useRatingInfo(email) {
  const [userRatingInfo, setUserRatingInfo] = useState({
    tier: "QUEEN",
    nickname: "QUEEN",
    reward: "100000000",
    totalMatches: "0",
    wins: "0",
  });

  const fetchData = async () => {
    try {
      const data = await getTotalRecord(email);
      setUserRatingInfo(data);
    } catch (error) {
      console.log("error", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return { userRatingInfo };
}

export default useRatingInfo;
