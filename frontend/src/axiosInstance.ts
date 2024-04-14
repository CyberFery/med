// src/axiosInstance.js
import axios from 'axios';
import { useAuthStore } from '@/stores/useAuthStore';

// Create an Axios instance
const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/', // Adjust this URL based on your environment
});

// Set the AUTH token for any request
axiosInstance.interceptors.request.use(config => {
  const { token } = useAuthStore();
  if (token.value) {
    config.headers.Authorization = `Bearer ${token.value}`;
  }
  return config;
}, error => {
  return Promise.reject(error);
});

export default axiosInstance;
