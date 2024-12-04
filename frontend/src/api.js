import axios from "axios";
const BASE_URL = "http://localhost:9090/v1";
// Register User
export const registerUser = async (userData) => {
  try {
    const response = await axios.post(`${BASE_URL}/users/register`, {
      name: userData.name,
      lastName: userData.lastName,
      userName: userData.userName,
      email: userData.email,
      password: userData.password,
    });
    return response.data;
  } catch (error) {
    console.error("Error registering user:", error.response?.data || error);
    throw error;
  }
};
