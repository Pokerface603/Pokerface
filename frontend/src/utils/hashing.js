import CrpytoJS from "crypto-js";

export function hashOpenviduTitle(title) {
  const encrypted = CrpytoJS.SHA256(title, "secretKey").toString();
  return encrypted;
}
