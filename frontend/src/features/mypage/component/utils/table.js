import blindTicket from "assets/images/ticket/bilnd-ticket.svg";
import normalTicket from "assets/images/ticket/normal-ticket.svg";
import emotionTicket from "assets/images/ticket/emotion-ticket.svg";

export const getTicketImg = (mode) => {
  switch (mode) {
    case "BLIND":
      return blindTicket;
    case "NORMAL":
      return normalTicket;
    case "EMOTION":
      return emotionTicket;
    default:
      return "error";
  }
};

export const getRatingUpDown = (number) => {
  const n = parseInt(number);
  if (n < 0) {
    return <text className="text-center text-blue-700">↓{Math.abs(n)}</text>;
  } else {
    return <text className="text-center text-red-700">↑{Math.abs(n)}</text>;
  }
};
