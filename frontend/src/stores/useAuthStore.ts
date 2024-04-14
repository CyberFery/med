// src/stores/useAuthStore.js
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const token = ref(localStorage.getItem('authToken'));

export function useAuthStore() {
  const router = useRouter();
  const isAuthenticated = computed(() => !!token.value);

  function setToken(newToken: string) {
    localStorage.setItem('authToken', newToken);
    token.value = newToken;
    axios.defaults.headers.common['Authorization'] = `Bearer ${newToken}`;
  }

  function clearToken() {
    localStorage.removeItem('authToken');
    token.value = "";
    delete axios.defaults.headers.common['Authorization'];
    router.push('/login');
  }

  return { isAuthenticated, setToken, clearToken };
}


