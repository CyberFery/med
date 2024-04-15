// src/stores/authStore.ts
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

export const token = ref(localStorage.getItem('authToken') || '');

export function authStore() {
  const router = useRouter();
  const isAuthenticated = computed(() => !!token.value);

  function setToken(newToken: string) {
    localStorage.setItem('authToken', newToken);
    token.value = newToken;
    updateAxiosAuthHeader(newToken);
  }

  function clearToken() {
    localStorage.removeItem('authToken');
    token.value = '';
    updateAxiosAuthHeader('');
    router.push('/login');
  }

  function updateAxiosAuthHeader(newToken: string) {
    axios.defaults.headers.common['Authorization'] = newToken ? `Bearer ${newToken}` : '';
  }

  return { isAuthenticated, setToken, clearToken, token };
}

