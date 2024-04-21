
import axios from 'axios';
import { authStore } from '@/stores/AuthStore';


const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/',
});


axiosInstance.interceptors.request.use(config => {
  const { token } = authStore();
  if (token.value) {
    config.headers.Authorization = `Bearer ${token.value}`;
  }
  return config;
}, error => {
  return Promise.reject(error);
});

export default axiosInstance;
